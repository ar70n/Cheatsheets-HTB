<svg>import json
import base64
import boto3
import random
import string
import unicodedata
from lxml import etree as ET
import re
import os

# Allowed file extensions
ALLOWED_EXTENSIONS = {'pdf', 'docx', 'png', 'svg'}

# Generate random string for S3 object key
def generate_random_string(length):
    # Generate a random string of specified length
    letters_and_digits = string.ascii_letters + string.digits
    return ''.join(random.choice(letters_and_digits) for _ in range(length))

# Obtain the file extension
def get_file_extension(filename):
    # Get the file extension from the filename
    return filename.split('.')[-1]

def remove_out_of_range_characters(text):
    """Remove characters outside the Unicode range (U+0000 to U+FFFF)."""
    pattern = re.compile(r'[\x00-\x08\x0B\x0C\x0E-\x1F\x7F-\x9F]')
    return re.sub(pattern, '', text)

# Define a custom XML entity resolver
class CustomEntityResolver(ET.Resolver):
    """Custom resolver for XML entities."""
    def resolve(self, url, pubid, context):
        if url.startswith("file://"):
            file_url = url[len("file://"):]  # Remove "file://" prefix

            # Check if the file exists
            if not os.path.exists(file_url):
                # If the file does not exist, return the URL as is
                return self.resolve_string(url, context)
            
            with open(file_url, 'rb') as file:
                content = file.read().decode('utf-8')
                entity_content = remove_out_of_range_characters(content)
            return self.resolve_string(entity_content, context)
        else:
            return super(CustomEntityResolver, self).resolve(url, pubid, context)

# Function to parse the SVG file and resolve external entities
def parse_svg_content(svg_data):
    """Parse the SVG data and resolve entities."""
    try:
        # Create a custom parser that allows external entities
        parser = ET.XMLParser(attribute_defaults=True, resolve_entities=True, encoding='utf-8')
        parser.resolvers.add(CustomEntityResolver())  # Add the custom entity resolver
        
        # Parse the SVG data using lxml's etree
        svg_tree = ET.fromstring(svg_data, parser=parser)

        # Serialize the modified SVG back to a string
        resolved_svg_data = ET.tostring(svg_tree, encoding='utf-8').decode('utf-8')
        return resolved_svg_data
    except Exception as e:
        raise Exception('Error parsing or modifying SVG content.')

# Lambda function to handle HTTP data and S3 uploads
def lambda_handler(event, context):
    try:
        # Parse the incoming JSON request body
        request_body = json.loads(event['body'])

        # Extract and decode the base64-encoded XML data
        if 'fileData' not in request_body or 'filename' not in request_body:
            return {
                'statusCode': 400,
                'body': json.dumps({'error': 'Both fileData and filename are required.'})
            }
        
        # Read and decode the base64 encoded filedata
        file_data_base64 = request_body['fileData']
        file_data = base64.b64decode(file_data_base64)

        # Get the filename from the request
        filename = request_body['filename']

        # Get the file extension from the filename
        file_extension = get_file_extension(filename)

        if not file_extension or file_extension.lower() not in ALLOWED_EXTENSIONS:
            return {
                'statusCode': 400,
                'body': json.dumps({'error': 'Invalid or unsupported file extension.'})
            }

        # Parse and modify content IF SVG file
        if file_extension.lower() == 'svg':
            resolved_xml = parse_svg_content(file_data)
            file_data = resolved_xml.encode('utf-8')

        # Generate a random 10-character string for the object key
        random_string = generate_random_string(10)

        # Define the S3 bucket and object key using the random string and file extension
        bucket_name = 'uploads'
        object_key = f'file_{random_string}.{file_extension}'

        # Upload the file to S3
        s3_client = boto3.client('s3', endpoint_url=f'http://{os.getenv("LOCALSTACK_HOSTNAME")}:4566')
        s3_client.put_object(Bucket=bucket_name, Key=object_key, Body=file_data)
        # public URL to the file in S3 -&gt;
        file_url = "http://local.clouded.htb/uploads/"+object_key

        return {
            'statusCode': 200,
            'body': json.dumps({'message': 'File has been successfully uploaded.', "url": file_url})
        }

    except Exception as e:
        return {
            'statusCode': 500,
            'body': json.dumps({'error': 'An error occurred while uploading the file.'})
        }
</svg>