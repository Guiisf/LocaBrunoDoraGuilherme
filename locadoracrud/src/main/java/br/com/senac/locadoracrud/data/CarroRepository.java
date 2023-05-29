package br.com.senac.locadoracrud.data;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.locadoracrud.model.Carro;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {
}
