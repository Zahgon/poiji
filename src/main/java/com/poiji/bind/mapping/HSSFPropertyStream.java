package com.poiji.bind.mapping;

import com.poiji.bind.PropertyUnmarshaller;
import com.poiji.exception.PoijiException;
import com.poiji.option.PoijiOptions;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hakan on 24.05.2020
 */
public final class HSSFPropertyStream implements PropertyUnmarshaller {

    private final PoijiOptions options;

    private InputStream inputStream;

    HSSFPropertyStream(InputStream inputStream, PoijiOptions options) {
        this.inputStream = inputStream;
        this.options = options;
    }

    @Override
    public <T> T unmarshal(Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T returnFromExcelFile(Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T returnFromEncryptedFile(Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
