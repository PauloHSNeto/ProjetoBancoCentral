package ibm.itau.projetobancocentral.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_dados")
public class Dados implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Change ot UUID
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") //formata a data
    private LocalDate data;
    private Double valor;
    @Column(name = "difference", scale = 2, precision = 2)
    private BigDecimal difference; //Change to BIGDECIMAL

    public Dados(LocalDate data, Double valor) {
        this.data = data;
        this.valor = valor;
    }
    public Dados(Long id, LocalDate data, Double valor) {
        this.id = id;
        this.data = data;
        this.valor = valor;
    }
}

