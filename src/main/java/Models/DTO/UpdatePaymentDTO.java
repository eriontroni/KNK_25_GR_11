package Models.DTO;

import java.math.BigDecimal;

public class UpdatePaymentDTO {
    private BigDecimal amount;
    private String payment_method;
    private String payment_status;

    public UpdatePaymentDTO() {}

    public UpdatePaymentDTO(BigDecimal amount, String payment_method, String payment_status) {
        this.amount = amount;
        this.payment_method = payment_method;
        this.payment_status = payment_status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
}
