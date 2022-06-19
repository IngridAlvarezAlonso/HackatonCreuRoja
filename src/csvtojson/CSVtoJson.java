package csvtojson;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVtoJson {
	
	static final String FICHERO_CSV= "./files/ejemplo1Columna.csv";
	static final String FICHERO_JSON= "./files/ejemplo1Columna.json";
	
	public static void main(String[] args) {
		CSVtoJson csvtoJson = new CSVtoJson();
		
		File input = new File(FICHERO_CSV);
        File output = new File(FICHERO_JSON);

        List<Map<?, ?>> data;
		try {
			data = readObjectsFromCsv(input);
			writeAsJson(data, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(csvSchema).readValues(file);

        return mappingIterator.readAll();
    }

    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }
}