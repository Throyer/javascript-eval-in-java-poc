package com.github.throyer.javascript.models;

import java.util.List;
import java.util.stream.Stream;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Parameters {
  public static enum Status {
    AWAITING_PAYMENT,
    PAYMENT_APPROVED,
    IN_DELIVERY_ROUTE,
    DELIVERED;
  }

  public static enum DeliveryType {
    STANDARD,
    EXPRESS,
    BUY_AND_PICKUP;
  }

  public static enum PaymentMethod {
    CARD,
    PIX,
    NU_PAY,
    APPLE_PAY,
    GOOGLE_PAY;
  }

  @Getter
  public static enum Origin {
    NON_MARKETPLACE("1P"),
    MARKETPLACE("3P"),
    MISTO("HYBRID");

    private String description;

    Origin(String description) {
      this.description = description;
    }
  }

  private Status status;
  private DeliveryType deliveryType;
  private List<PaymentMethod> paymentMethods;

  @Schema(example = "1P", requiredMode = RequiredMode.REQUIRED)
  private Origin origin;
  private Boolean controlled;
  private Boolean termolabil;
  private Boolean hasDeadline;
  private Boolean deadlineIsLate;
  private Boolean hasEta;
  private Boolean etaIsEarly;
  private Boolean etaIsLate;

  public String getOrigin() {
    return this.origin.getDescription();
  }

  public void setOrigin(String origin) {
    this.origin = Stream.of(Origin.values())
      .filter(value -> value.description.equals(origin))
      .findFirst()
      .orElse(null);
  }
}
