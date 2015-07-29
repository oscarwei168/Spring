<#-- variables definition -->
<#assign style>font-size: 16; font-weight: bold;</#assign>

<#-- HTML template area -->
<p style="${style}">Dear ${applicant!"Unknown"}, </p>
<p style="${style}">您的休假已經全部審核完畢.</p>
<p style="${style}">相關資訊如下:</p>

<ul>
    <li style="${style}">請假日期 : ${startDate?datetime} ~ ${endDate?datetime}</li>
    <li style="${style}">職務代理人 : ${dutyAgent!"Unknown"}</li>
</ul>

<p style="${style}">審核流程可連至 <a href="${dtsServerUrl}">DTS系統</a></p>
<br/><br/>
<#include "copyright.html">