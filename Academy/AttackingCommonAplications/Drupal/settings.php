/**
 * Database settings:
 *
 * The $databases array specifies the database connection or
 * connections that Drupal may use.  Drupal is able to connect
 * to multiple databases, including multiple types of databases,
 * during the same request.
 *
 * Each database connection is specified as an array of settings,
 * similar to the following:
 * @code
 * array(
 *   'driver' => 'mysql',
 *   'database' => 'databasename',
 *   'username' => 'username',
 *   'password' => 'password',
 *   'host' => 'localhost',
 *   'port' => 3306,
 *   'prefix' => 'myprefix_',
 *   'collation' => 'utf8_general_ci',
 * );
 * @endcode
 *
 * The "driver" property indicates what Drupal database driver the
 * connection should use.  This is usually the same as the name of the
 * database type, such as mysql or sqlite, but not always.  The other
 * properties will vary depending on the driver.  For SQLite, you must
 * specify a database file name in a directory that is writable by the
 * webserver.  For most other drivers, you must specify a
 * username, password, host, and database name.
 *
 * Some database engines support transactions.  In order to enable
 * transaction support for a given database, set the 'transactions' key
 * to TRUE.  To disable it, set it to FALSE.  Note that the default value
 * varies by driver.  For MySQL, the default is FALSE since MyISAM tables
 * do not support transactions.
 *
 * For each database, you may optionally specify multiple "target" databases.
 * A target database allows Drupal to try to send certain queries to a
 * different database if it can but fall back to the default connection if not.
 * That is useful for master/slave replication, as Drupal may try to connect
 * to a slave server when appropriate and if one is not available will simply
 * fall back to the single master server.
 *
 * The general format for the $databases array is as follows:
 * @code
 * $databases['default']['default'] = $info_array;
 * $databases['default']['slave'][] = $info_array;
 * $databases['default']['slave'][] = $info_array;
 * $databases['extra']['default'] = $info_array;
 * @endcode
 *
 * In the above example, $info_array is an array of settings described above.
 * The first line sets a "default" database that has one master database
 * (the second level default).  The second and third lines create an array
 * of potential slave databases.  Drupal will select one at random for a given
 * request as needed.  The fourth line creates a new database with a name of
 * "extra".
 *
 * For a single database configuration, the following is sufficient:
 * @code
 * $databases['default']['default'] = array(
 *   'driver' => 'mysql',
 *   'database' => 'databasename',
 *   'username' => 'username',
 *   'password' => 'password',
 *   'host' => 'localhost',
 *   'prefix' => 'main_',
 *   'collation' => 'utf8_general_ci',
 * );
 * @endcode
 *
 * You can optionally set prefixes for some or all database table names
 * by using the 'prefix' setting. If a prefix is specified, the table
 * name will be prepended with its value. Be sure to use valid database
 * characters only, usually alphanumeric and underscore. If no prefixes
 * are desired, leave it as an empty string ''.
 *
 * To have all database names prefixed, set 'prefix' as a string:
 * @code
 *   'prefix' => 'main_',
 * @endcode
 * To provide prefixes for specific tables, set 'prefix' as an array.
 * The array's keys are the table names and the values are the prefixes.
 * The 'default' element is mandatory and holds the prefix for any tables
 * not specified elsewhere in the array. Example:
 * @code
 *   'prefix' => array(
 *     'default'   => 'main_',
 *     'users'     => 'shared_',
 *     'sessions'  => 'shared_',
 *     'role'      => 'shared_',
 *     'authmap'   => 'shared_',
 *   ),
 * @endcode
 * You can also use a reference to a schema/database as a prefix. This may be
 * useful if your Drupal installation exists in a schema that is not the default
 * or you want to access several databases from the same code base at the same
 * time.
 * Example:
 * @code
 *   'prefix' => array(
 *     'default'   => 'main.',
 *     'users'     => 'shared.',
 *     'sessions'  => 'shared.',
 *     'role'      => 'shared.',
 *     'authmap'   => 'shared.',
 *   );
 * @endcode
 * NOTE: MySQL and SQLite's definition of a schema is a database.
 *
 * Advanced users can add or override initial commands to execute when
 * connecting to the database server, as well as PDO connection settings. For
 * example, to enable MySQL SELECT queries to exceed the max_join_size system
 * variable, and to reduce the database connection timeout to 5 seconds:
 *
 * @code
 * $databases['default']['default'] = array(
 *   'init_commands' => array(
 *     'big_selects' => 'SET SQL_BIG_SELECTS=1',
 *   ),
 *   'pdo' => array(
 *     PDO::ATTR_TIMEOUT => 5,
 *   ),
 * );
 * @endcode
 *
 * WARNING: These defaults are designed for database portability. Changing them
 * may cause unexpected behavior, including potential data loss.
 *
 * @see DatabaseConnection_mysql::__construct
 * @see DatabaseConnection_pgsql::__construct
 * @see DatabaseConnection_sqlite::__construct
 *
 * Database configuration format:
 * @code
 *   $databases['default']['default'] = array(
 *     'driver' => 'mysql',
 *     'database' => 'databasename',
 *     'username' => 'username',
 *     'password' => 'password',
 *     'host' => 'localhost',
 *     'prefix' => '',
 *   );
 *   $databases['default']['default'] = array(
 *     'driver' => 'pgsql',
 *     'database' => 'databasename',
 *     'username' => 'username',
 *     'password' => 'password',
 *     'host' => 'localhost',
 *     'prefix' => '',
 *   );
 *   $databases['default']['default'] = array(
 *     'driver' => 'sqlite',
 *     'database' => '/path/to/databasefilename',
 *   );
 * @endcode
 */
$databases = array (
  'default' =>
  array (
    'default' =>
    array (
      'database' => 'drupal_qa',
      'username' => 'drupaladm',
      'password' => 'HTB_@cademy_Drupa1!',
      'host' => 'localhost',
      'port' => '',
      'driver' => 'mysql',
      'prefix' => '',
    ),
  ),
);


