package com.poiji.bind.mapping;

import com.poiji.option.PoijiOptions;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.Attributes2Impl;

/**
 * Created by hakan on 26.04.2020
 */
class XSSFSheetXMLPoijiHandler extends XSSFSheetXMLHandler {

    private final Styles stylesTable;

    private final PoijiOptions poijiOptions;

    private final PoijiLogCellFormat cellFormat;

    XSSFSheetXMLPoijiHandler(Styles styles, Comments comments, SharedStrings strings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults, PoijiOptions poijiOptions) {
        super(styles, comments, strings, sheetContentsHandler, dataFormatter, formulasNotResults);
        this.stylesTable = styles;
        this.poijiOptions = poijiOptions;
        this.cellFormat = this.poijiOptions.getPoijiCellFormat();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
