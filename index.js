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
 * @returns {boolean} define se comunicação é elegível ou não.
 */
function eligible(parameters) {
  return parameters.paymentMethods.includes('CARD');
  // função que define os critérios de quando essa comunicação é ou não elegível para o disparo.
}