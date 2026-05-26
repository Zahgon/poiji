package com.poiji.bind.mapping;

import com.poiji.option.PoijiOptions;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew 2018/09/01
 */
final class WorkBookContentHandler implements ContentHandler {

    private WorkBookSheet individualSheet;

    private final List<WorkBookSheet> sheets = new ArrayList<>();

    private final PoijiOptions options;

    WorkBookContentHandler(final PoijiOptions options) {
        this.options = options;
    }

    protected List<WorkBookSheet> getSheets() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void startDocument() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void endDocument() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void endPrefixMapping(String prefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void processingInstruction(String target, String data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void skippedEntity(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
