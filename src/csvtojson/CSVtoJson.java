package csvtojson;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVtoJson {
	
	static final String FICHERO_CSV= "./files/ejemplo2Columna.csv";
	static final String FICHERO_JSON= "./files/ejemplo2Columna.json";
	
	public static void main(String[] args) {
		CSVtoJson csvtoJson = new CSVtoJson();
		
		File input = new File(FICHERO_CSV);
        File output = new File(FICHERO_JSON);

        List<Map<String,Integer>> data;
		try {
			data = readObjectsFromCsv(input);
			writeAsJson(data, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Map<String, Integer>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        
        
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<String, Integer>> mappingIterator = csvMapper.reader(Map.class).with(csvSchema).readValues(file);

        return mappingIterator.readAll();
    }

    public static void writeAsJson(List<Map<String, Integer>> data, File file) throws IOException {
        JsonMapper mapper = new JsonMapper();
        mapper.writeValue(file, data);
    }
}