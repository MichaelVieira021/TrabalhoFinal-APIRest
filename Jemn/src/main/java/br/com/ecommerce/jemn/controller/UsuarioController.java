package br.com.ecommerce.jemn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ecommerce.jemn.dto.usuario.UsuarioLoginRequestDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioLoginResponseDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioRequestDTO;
import br.com.ecommerce.jemn.dto.usuario.UsuarioResponseDTO;
import br.com.ecommerce.jemn.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ecommerce/usuarios")
@CrossOrigin("*")
@Tag(name = "Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Pesquisa e traz todos os usuarios cadastrados ", method = "GET", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping//ADMIN
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UsuarioResponseDTO>> obterTodos(){
		return ResponseEntity.ok(usuarioService.obterTodos());
	}

	@Operation(summary = "Pesquisa e traz por id um usuario cadastrado ", method = "GET", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.obterPorId(id));
	}

	@Operation(summary = "Adiciona novo usuario ", method = "POST", description = "Metodo criado para todos ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuarioRequest){
		usuarioService.uniqueEMAILeTEL(usuarioRequest, 0L);
		return ResponseEntity.status(201).body(usuarioService.adicionar(usuarioRequest));
	}
	
	@Operation(summary = "Atualiza um usuario cadastrado ", method = "PUT", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequest){
		usuarioService.uniqueEMAILeTEL(usuarioRequest, id);
		return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioRequest));
	}
	
	@Operation(summary = "Deleta um usuario cadastrado ", method = "DELETE", description = "Metodo criado somente para o ADMIN ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		usuarioService.deletar(id);
		return ResponseEntity.status(204).build();
	}
	
	@Operation(summary = "Loga um usuario existente ", method = "POST", description = "Metodo criado para todos (serve para conectar e obter o token de autorização para alguns metodos.) ")
	@ApiResponse(responseCode = "200", description = "Busca por ID realizada com sucesso")
	@ApiResponse(responseCode = "422", description = "Dados de requisição inválidos")
	@ApiResponse(responseCode = "400", description = "Paramentros inválidos")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos Dados por ID")
	@PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> logar(@RequestBody UsuarioLoginRequestDTO usuariologinRequest){
        UsuarioLoginResponseDTO usuarioLogado = usuarioService.logar(usuariologinRequest.getEmail(), usuariologinRequest.getSenha());
        return ResponseEntity.status(200).body(usuarioLogado);
    }
}
