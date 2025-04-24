package com.github.throyer.javascript.utils;

import java.util.stream.Stream;

import com.github.throyer.javascript.models.Parameters.DeliveryType;
import com.github.throyer.javascript.models.Parameters.Origin;
import com.github.throyer.javascript.models.Parameters.PaymentMethod;
import com.github.throyer.javascript.models.Parameters.Status;

public class JavascriptUtils {
  public static final String FUNCTION_EXAMPLE = """
      /**      
       * @param {Parameters} parameters
       * @returns {boolean} define se comunicação é elegível ou não.
       */
      function eligible(parameters) {
        // função que define os critérios de quando essa comunicação é ou não elegível para o disparo.
      }""";


  public static String func() {
    var status = String.join(" | ", Stream.of(Status.values())
    .map(Status::name)
    .map(value -> String.format("\"%s\"", value))
    .toList());

    var delivery = String.join(" | ", Stream.of(DeliveryType.values())
    .map(DeliveryType::name)
    .map(value -> String.format("\"%s\"", value))
    .toList());

    var payments = String.join(" | ", Stream.of(PaymentMethod.values())
    .map(PaymentMethod::name)
    .map(value -> String.format("\"%s\"", value))
    .toList());

    var origins = String.join(" | ", Stream.of(Origin.values())
    .map(Origin::getDescription)
    .map(value -> String.format("\"%s\"", value))
    .toList());

    return """
    /**
     * @typedef {%s} Status
     * @typedef {%s} DeliveryType
     * @typedef {%s} PaymentMethod
     * @typedef {%s} Origin
     *
     * @typedef {Object} Parameters
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
     * @param {Parameters} parameters
     * @returns {boolean} define se comunicação é elegível ou não.
     */
    function eligible(parameters) {
      // função que define os critérios de quando essa comunicação é ou não elegível para o disparo.
    }
    """.formatted(status, delivery, payments, origins, FUNCTION_EXAMPLE);
  }
}
