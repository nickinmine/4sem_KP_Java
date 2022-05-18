package ru.mirea.coursework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.mirea.coursework.entity.Client;
import ru.mirea.coursework.repository.ClientRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {
    @Autowired
    private final ClientRepository clientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByName(username);
    }

    public void create(String name, String pass, String email, String role, String address) {
        Client client = new Client();
        client.setName(name);
        client.setPass(bCryptPasswordEncoder.encode(pass));
        client.setEmail(email);
        client.setRole(role);
        client.setAddress(address);
        clientRepository.save(client);
    }

    public void saveChanges(Client client) {
        clientRepository.save(client);
    }

    public void delete(String name) {
        clientRepository.deleteByName(name);
    }

    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    public List<Client> readAll() {
        return clientRepository.findAll();
    }
}
