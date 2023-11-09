package com.example.projetFilRouge.helper;


import com.example.projetFilRouge.models.Tutorial;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id","adult","belongs_to_collection","budget","genres","homepage","id", "imdb_id","original_language","original_title","overview","popularity","poster_path","production_companies","production_countries", "release_date","revenue", "runtime","spoken_languages", "status", "tagline", "title", "video", "vote_average", "vote_count"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Tutorial> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Tutorial tutorial = new Tutorial(
                        Integer.parseInt(csvRecord.get("Id")),
                        csvRecord.get("adult"),
                        csvRecord.get("belongs_to_collection"),
                        Long.parseLong(csvRecord.get("budget")),
                        csvRecord.get("genres"),
                        csvRecord.get("homepage"),
                        Integer.parseInt(csvRecord.get("id")),
                        csvRecord.get("imdb_id"),
                        csvRecord.get("original_language"),
                        csvRecord.get("original_title"),
                        csvRecord.get("overview"),
                        csvRecord.get("popularity"),
                        csvRecord.get("poster_path"),
                        csvRecord.get("production_companies"),
                        csvRecord.get("production_countries"),
                        csvRecord.get("release_date"),
                        Long.parseLong(csvRecord.get("revenue")),
                        csvRecord.get("runtime"),
                        csvRecord.get("spoken_languages"),
                        csvRecord.get("status"),
                        csvRecord.get("tagline"),
                        csvRecord.get("title"),
                        csvRecord.get("video"),
                        csvRecord.get("vote_average"),
                        Long.parseLong(csvRecord.get("vote_count"))



                );

                tutorials.add(tutorial);
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
