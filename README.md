# TestRESTful
RESTful HelloWorld.<br>
The web service that implements the following methods:<br>
<ol>
<li>String getCreate(String schemaName, String tableName) - returns DDL to create
said existing database table. The script should contain the DDL creation
primary key.</li>
<li>String getSelect(String schemaName, String tableName) – returns query of type 
SELECT &lt;a list of fields separated by commas&gt; FROM &lt;specified table&gt;
WHERE &lt;field primary key&gt; = ? (or a list of fields in the case of a composite PK)</li>
<li>String getUpdate(String schemaName, String tableName) - returns query of type
UPDATE &lt;specified table&gt; SET &lt;field1&gt; = ?, &lt;field2&gt; = ? ... WHERE &lt;field primary key&gt; = ?
(or a list of fields in the case of a composite PK)</li>
</ol>
