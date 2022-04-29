package ibm.itau.projetobancocentral.repositories;

import ibm.itau.projetobancocentral.entities.Dados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DadosRepository extends JpaRepository<Dados, Long> {

    List<Dados> findByYear(int year);
    List<Dados> findByDay(int day);
    List<Dados> findByMonth(String month);

    List<Dados> findAboveValue(double value);
    List<Dados> findBelowValue(double value);

    List<Dados> findByYearAboveValue(int year, double value);
    List<Dados> findByYearBelowValue(int year, double value);

//    public Dados findByMaxValue();
//    public Dados findByMinValue();
//
//    public Dados findByMaxValueOfYear(int year);
//    public Dados findByMinValueOfYear(int year);


}
