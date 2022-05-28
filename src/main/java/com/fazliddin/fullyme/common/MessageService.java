package com.fazliddin.fullyme.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageService {

    private static MessageSource messageSource;

    @Autowired
    public void helper(MessageSource messageSource) {
        MessageService.messageSource = messageSource;
    }

    public static String getMessage(String key) {
        return messageSource.getMessage(key, new Object[0], LocaleContextHolder.getLocale());
    }

    public static String merge(String action, String termin) {
        return String.format(getMessage(action), getMessage(termin));
    }


    /*==================SUCCESS===================*/

    public static String successEdit(String termin) {
        return merge("SUCCESS_EDIT", termin);
    }

    public static String successSave(String termin) {
        return merge("SUCCESS_SAVE", termin);
    }

    public static String successDelete(String termin) {
        return merge("SUCCESS_DELETE", termin);
    }
    public static String successSend(String termin) {
        return merge("SUCCESS_SEND", termin);
    }


    /*===================ERROR===================*/

    public static String cannotDelete(String termin) {
        return merge("CANNOT_DELETE", termin);
    }

    public static String notFound(String termin) {
        return merge("NOT_FOUND", termin);
    }

    public static String alreadyExists(String termin) {
        return merge("ALREADY_EXISTS", termin);
    }


}
