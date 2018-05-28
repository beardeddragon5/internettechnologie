<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" />
  <xsl:template match="/">
    <html>
      <head>
        <title>
          <xsl:value-of select="/Vorlesung/Titel"/>
        </title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous" />
      </head>
      <body>
        Titel: <xsl:value-of select="/Vorlesung/Titel"/><br/>
        Semester: <xsl:value-of select="/Vorlesung/Semester"/><br/>
        Hochschule: <xsl:value-of select="/Vorlesung/Hochschule"/><br/>
        SWS: <xsl:value-of select="/Vorlesung/SWS"/><br/>
        <ul>
          <xsl:for-each select="/Vorlesung/Kapitel">
            <li>
              <xsl:value-of select="Name"/>
              <xsl:if test="@quelle">
                <br/>
                Autor: <xsl:value-of select="id(@quelle)/Autor"/> <br/>
                Titel: <xsl:value-of select="id(@quelle)/Titel"/> <br/>
              </xsl:if>
            </li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
