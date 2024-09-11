<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Demo</title>
</head>
<body>
<@ssiMacro name="HEADER" id=""/>

<#if title??>
    <h1>${title}</h1>
<#else >
    <h1>Default Title</h1>
</#if>


<@ssiMacro name="FOOTER" id=""/>
</body>
</html>