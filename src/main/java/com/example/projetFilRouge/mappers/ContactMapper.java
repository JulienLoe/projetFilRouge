//package com.example.projetFilRouge.mappers;
//
//import com.example.projetFilRouge.entities.Contact;
//import com.example.projetFilRouge.models.ContactDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//import java.time.LocalDate;
//
//@Mapper
//public interface ContactMapper {
//
//    @Mapping(source = "blabla", target = "firstName")
//    Contact contactDTOToContact(ContactDTO dto);
//
//    @Mapping(source = "firstName", target = "blabla")
//    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "convertDate")
//    ContactDTO contactToContactDto(Contact contact);
//
//
//
//    @Named("convertDateToAge")
//    public static Integer convertDateToAge(LocalDate date) {
//        LocalDate now = LocalDate.now();
//        int age = now.getYear() - date.getYear();
//
//        if (now.minusYears(age).isBefore(date)) {
//            age--;
//        }
//
//        return age;
//    }
//
//    @Named("convertDate")
//    public static String convertDate(LocalDate date) {
//        String string = date.toString();
//
//        return string;
//    }
//}
