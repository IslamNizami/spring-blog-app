package itbrains.az.blogpage2.services.impl;

import itbrains.az.blogpage2.dtos.roledtos.RoleDto;
import itbrains.az.blogpage2.models.Role;
import itbrains.az.blogpage2.repositories.RoleRepository;
import itbrains.az.blogpage2.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> result = roles.stream().map(role->modelMapper.map(role,RoleDto.class)).collect(Collectors.toList());
        return result;
    }
}
