package com.huseyin.youcontribute.controllers.resources;

import com.huseyin.youcontribute.models.Repository;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class RepositoryResource {

    private String name;
    private String organization;

    public static RepositoryResource createFor(Repository repository) {
        return RepositoryResource.builder()
                .name(repository.getRepository())
                .organization(repository.getOrganization())
                .build();
    }

    public static List<RepositoryResource> createFor(List<Repository> repositories) {
        return repositories.stream().map(RepositoryResource::createFor).collect(Collectors.toList());
    }
}
