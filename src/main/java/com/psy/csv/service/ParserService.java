package com.psy.csv.service;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.psy.csv.dto.CsvBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ParserService {

    public static final char DEFAULT_SEPARATOR = ';';
    public static final char DEFAULT_QUOTE = '"';

    /**
     * Parse file to list of (clazz)objects
     *
     * @param file      - multipart csv file
     * @param clazz     - dto class Class (clazz inherited from CsvBean)
     * @param separator - custom separator
     * @param quoteChar - custom quote
     * @return List<CsvBean>
     * @throws Exception if something goes wrong
     */
    public List<CsvBean> parse(MultipartFile file, Class clazz, String separator, String quoteChar) throws Exception {
        List<CsvBean> list;
        try (Reader reader = new InputStreamReader(file.getInputStream())) {

            ColumnPositionMappingStrategy<CsvBean> ms = new ColumnPositionMappingStrategy<>();
            ms.setType(clazz);

            CsvToBean<CsvBean> cb = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .withMappingStrategy(ms)
                    .withSeparator(getSeparator(separator))
                    .withQuoteChar(getQuoteChar(quoteChar))
                    .build();
            list = cb.parse();
        } catch (Exception e) {
            throw new Exception("Parsing error");
        }

        return list;
    }


    /**
     * Get custom csv data separator char from string
     *
     * @param separator - string
     * @return char separator;
     */
    private Character getSeparator(String separator) {
        if (isNull(separator)) return DEFAULT_SEPARATOR;
        return separator.charAt(0);
    }

    /**
     * Get custom quote char from string
     *
     * @param quote string
     * @return char quote
     */
    private Character getQuoteChar(String quote) {
        if (isNull(quote)) return DEFAULT_QUOTE;
        return quote.charAt(0);
    }


}
