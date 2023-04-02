package com.ufersa.sistemaagendamento.service.services;

import com.ufersa.sistemaagendamento.infrastructure.interfaces.IUserRepository;
import com.ufersa.sistemaagendamento.model.entities.User;
import com.ufersa.sistemaagendamento.model.requests.UserRequest;
import com.ufersa.sistemaagendamento.model.responses.UserResponse;
import com.ufersa.sistemaagendamento.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse AddUser(UserRequest userRequest) {

        var registeredUser = GetUserByLoginData(userRequest.email, userRequest.senha);
        if(!registeredUser.isEmpty()){
            return new UserResponse(registeredUser.get().getAdmin(), "Bem-vindo de volta 🫡");
        }

        var isRegisteredUser = IsRegisteredUser(userRequest.email);
        if(isRegisteredUser){
            return new UserResponse(null, "Vish, senha incorreta ;/");
        }

        var user = new User();
        user.setEmail(userRequest.email);
        user.setSenha(userRequest.senha);
        user.setNome(userRequest.nome);
        user.setTelefone(userRequest.telefone);
        var response = userRepository.save(user);
        return new UserResponse(response.getAdmin(), "Usuário cadastrado 🖖");
    }

    @Override
    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> GetUserByLoginData(String email, String senha) {
        var allUsers = GetAllUsers();
        return allUsers.stream().filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha)).findFirst();
    }

    @Override
    public Boolean IsRegisteredUser(String email) {
        var allUsers = GetAllUsers();
        var registeredUser = allUsers.stream().filter(u -> u.getEmail().equals(email)).findFirst();
        if(!registeredUser.isEmpty()){
            return true;
        }
        return false;
    }
}