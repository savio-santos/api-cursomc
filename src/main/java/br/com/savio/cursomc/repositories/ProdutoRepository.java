package br.com.savio.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.savio.cursomc.domain.Categoria;
import br.com.savio.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> searchByNomeAndCategoria(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
	/*	
	 *	@Transactional(readOnly=true)
	 *	Page<Produto> findDistinctByNomeContainingAndCategoriasIn( String nome,  List<Categoria> categorias, Pageable pageRequest);
	 *	//obs: mesma query do (searchByNomeAndCategoria) mas com o padrao de nomes do spring data. 
	 */
	
	Page<Produto> findDistinctByNomeContaining( String nome, Pageable pageRequest);

	
	
	

	
}
