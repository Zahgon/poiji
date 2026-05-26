package com.poiji.bind.mapping;

import com.poiji.bind.PoijiInputStream;
import com.poiji.exception.PoijiException;
import com.poiji.option.PoijiOptions;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.IOException;

/**
 * Created by hakan on 08/03/2018.
 */
final class HSSFUnmarshallerStream extends HSSFUnmarshaller {

    private final PoijiInputStream<?> poijiInputStream;

    HSSFUnmarshallerStream(PoijiInputStream<?> poijiInputStream, PoijiOptions options) {
        super(options);
        this.poijiInputStream = poijiInputStream;
    }

    @Override
    protected Workbook workbook() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
