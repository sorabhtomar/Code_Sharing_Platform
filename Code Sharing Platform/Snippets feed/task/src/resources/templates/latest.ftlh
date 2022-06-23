<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Latest</title>
    <link rel="stylesheet" href="../css/style.css">
  </head>
  <body>
    <#list codes as code>
        <span class="load_date">${code.getFormattedCreateTime()}</span>
        <pre class="code_snippet">${code.getCode()}</pre>
    </#list>
  </body>
</html>