package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.mvc.With;

@With(Seguranca.class)
public class Application extends Controller {

    public static void index() {
        render();
    }

}