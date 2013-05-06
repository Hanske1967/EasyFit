set MYSQL="C:\Program Files\MySQL\MySQL Server 5.6\bin"
set PATH=%MYSQL%;%MYSQL%\bin
set BACKUP_DIR=%DATE%
set BACKUP_DIR=%BACKUP_DIR:/=-%
mkdir %BACKUP_DIR%
cd %BACKUP_DIR%
mysqldump easyfit --force --user=hans --password=Tomytomy --quote-names > backup.sql
