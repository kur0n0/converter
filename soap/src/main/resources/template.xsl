<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <person>
            <xsl:attribute name="name">
                <xsl:value-of select="person/name/text()"/>
            </xsl:attribute>
            <xsl:attribute name="surname">
                <xsl:value-of select="person/surname/text()"/>
            </xsl:attribute>
            <xsl:attribute name="patronymic">
                <xsl:value-of select="person/patronymic/text()"/>
            </xsl:attribute>
            <xsl:attribute name="birthDate">
                <xsl:value-of select="person/birthDate/text()"/>
            </xsl:attribute>
            <xsl:attribute name="gender">
                <xsl:value-of select="person/gender/text()"/>
            </xsl:attribute>
            <document>
                <xsl:attribute name="series">
                    <xsl:value-of select="person/document/series/text()"/>
                </xsl:attribute>
                <xsl:attribute name="number">
                    <xsl:value-of select="person/document/number/text()"/>
                </xsl:attribute>
                <xsl:attribute name="type">
                    <xsl:value-of select="person/document/type/text()"/>
                </xsl:attribute>
                <xsl:attribute name="issueDate">
                    <xsl:value-of select="person/document/issueDate/text()"/>
                </xsl:attribute>
            </document>
        </person>
    </xsl:template>
</xsl:stylesheet>