package com.github.throyer.javascript.controllers;

import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poc")
public class PocController {
  @GetMapping
  public Object index() throws ScriptException, NoSuchMethodException {
      
    var manager = new ScriptEngineManager();
    var engine = manager.getEngineByName("js");

    // language=javascript
    var parameters = engine.eval("""
    ({
      "deliveryType": "D+1",
      "paymentTypes": ["PIX"],
      "origin": "1P"
    })    
    """);

    // language=javascript
    var script = 
    """
    function eligible(parameters) {
      if (parameters.deliveryType === 'EXPRESS') {
        return false;
      }

      if (parameters.paymentTypes.includes('PIX')) {
        return false;
      }

      return true;
    }
    """;

    engine.eval(script);

    var invocable = (Invocable) engine;

    var result = Boolean.class.cast(invocable.invokeFunction("eligible", parameters));

    return Map.of("eligible", result);
  }
}
