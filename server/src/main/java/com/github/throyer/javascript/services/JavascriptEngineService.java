package com.github.throyer.javascript.services;

import java.text.MessageFormat;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import com.github.throyer.javascript.models.Parameters;
import com.github.throyer.javascript.utils.JSON;

@Service
public class JavascriptEngineService {
  private final ScriptEngine engine;

  public JavascriptEngineService() {
    var manager = new ScriptEngineManager();
    this.engine = manager.getEngineByName("js");
  }

  public Boolean eligible(Parameters parameters) {
    try {
      // language=javascript
      var script = """
            /**
             * @typedef {"AWAITING_PAYMENT" | "PAYMENT_APPROVED" | "IN_DELIVERY_ROUTE" | "DELIVERED"} Status
             * @typedef {"STANDARD" | "EXPRESS" | "BUY_AND_PICKUP"} DeliveryType
             * @typedef {"CARD" | "PIX" | "NU_PAY" | "APPLE_PAY" | "GOOGLE_PAY"} PaymentMethod
             * @typedef {"1P" | "3P" | "HYBRID"} Origin
             *
             * @typedef {Object} OrderParameters
             * @property {Status} status status atual do pedido.
             * @property {DeliveryType} deliveryType tipo de entrega.
             * @property {Array<PaymentMethod>} paymentMethods métodos de pagamento.
             * @property {Origin} origin origem do pedido sendo Hybrid o pedido misto.
             * @property {boolean} controlled se possui algum item com produto controlado (exige receita medica).
             * @property {boolean} termolabil se possui algum item com produto termolábil (com embalagem especifica para manter a temperatura).
             * @property {boolean} hasDeadline se o pedido possui um prazo de entrega (compromisso de entrega definido no momento da compra).
             * @property {boolean} deadlineIsLate se o prazo de entrega do compromisso já esta em atraso.
             * @property {boolean} hasEta se o pedido possui um prazo de entrega estipulado na transportadora.
             * @property {boolean} etaIsEarly se o prazo de entrega da transportadora está com adiantado em relação ao do compromisso.
             * @property {boolean} etaIsLate se o prazo de entrega da transportadora está com atraso.
             *
             * @param {OrderParameters} parameters
             * @returns {boolean} define comunicação é elegível ou não.
             */
            function eligible(parameters) {
              console.log(JSON.stringify(parameters))
              if (parameters.status === 'DELIVERED') {
                return false;
              }

              if (parameters.deliveryType === 'EXPRESS') {
                return false;
              }

              if (parameters.paymentMethods.includes('PIX')) {
                return false;
              }

              return true;
            }
          """;

      engine.eval(script);

      var invocable = (Invocable) engine;

      return Boolean.class.cast(invocable.invokeFunction("eligible", parameters(parameters)));

    } catch (Exception exception) {
      exception.printStackTrace();

      return false;      
    }
  }

  private Object parameters(Parameters parameters) throws ScriptException {
    var json = JSON.stringify(parameters);
    return engine.eval(MessageFormat.format("({0})", json));
  }
}
