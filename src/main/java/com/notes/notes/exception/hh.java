package com.notes.notes.exception;

public class hh {
}


@ExceptionHandler(NoteNotFoundException.class)
// El méttodo devuelve un ModelAndView, que nos permite especificar tanto
// la vista (la página HTML) como los datos a mostrar.
public ModelAndView handleNoteNotFound(NoteNotFoundException ex) {

    // 1. Crear un ModelAndView. Usaremos una nueva plantilla: 'error-404'.
    ModelAndView modelAndView = new ModelAndView("error-404");

    // 2. Añadir el mensaje de error al modelo.
    modelAndView.addObject("errorMessage", ex.getMessage());
    modelAndView.addObject("status", HttpStatus.NOT_FOUND.value()); // Código 404

    // 3. Establecer el código de estado HTTP 404.
    modelAndView.setStatus(HttpStatus.NOT_FOUND);

    return modelAndView;
}