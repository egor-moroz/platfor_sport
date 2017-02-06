package configuration;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

import java.sql.Types;

/**
 * Created by EgorPC on 06.02.2017.
 */
public class HsqlDataTypeFactory extends DefaultDataTypeFactory {

    @Override
    public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException {
        if (sqlType == Types.BOOLEAN) {
            return DataType.BOOLEAN;
        }
        return super.createDataType(sqlType, sqlTypeName);
    }
}

