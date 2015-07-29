<#-- variables definition -->
<#assign style>font-size: 16; font-weight: bold;</#assign>

<#-- HTML template area -->
<p style="${style}">Dear ${applicant!"Unknown"}, </p>
<p style="${style}">您申請的假單被職務代理人/單位主管拒絕, 請上系統確認.<br/>
    請假相關資訊如下 : </p>

<ul>
    <li style="${style}">請假日期 : ${startDate?datetime} ~ ${endDate?datetime} </li>
    <li style="${style}">拒絕人 : ${dutyAgent!"Unknown"} </li>
    <li style="${style}">拒絕原因 : ${description!""}</li>
</ul>

<p style="${style}">請連至 <a href="${dtsServerUrl}">DTS系統</a></p>
<br/><br/>
<#include "copyright.html">