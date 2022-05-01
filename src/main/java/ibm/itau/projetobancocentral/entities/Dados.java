package ibm.itau.projetobancocentral.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dados")
public class Dados implements Serializable, Comparable<Dados> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") //formata a data
    private LocalDate data;
    private Double valor;
    @Column(name = "diferenca", columnDefinition = "Decimal(10,2) default '0.00' ")
    private Double difference;

    public Dados(LocalDate data, double valor) {
        this.data = data;
        this.valor = valor;
    }
    @Override
    public int compareTo(Dados o) {
        return Double.compare(this.valor, o.valor);
    }
    public int compareByDate(Dados o) {
        return this.data.compareTo(o.data);
    }
}

