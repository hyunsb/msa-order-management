package com.sparta.msa_exam.gateway.loadbalancer;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class WeightServiceInstanceSupplier implements ServiceInstanceListSupplier {

    private final List<ServiceInstance> instances = new ArrayList<>();
    private final Random random = new Random();

    public WeightServiceInstanceSupplier() {
        instances.add(new CustomServiceInstance("product-service-1", "localhost", 19093, 70));
        instances.add(new CustomServiceInstance("product-service-2", "localhost", 19094, 30));
    }

    @Override
    public String getServiceId() {
        return "product-service";
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        List<ServiceInstance> selectedInstances = new ArrayList<>();
        for (ServiceInstance instance : instances) {
            CustomServiceInstance serviceInstance = (CustomServiceInstance) instance;
            if (random.nextInt(100) < serviceInstance.getWeight()) {
                selectedInstances.add(instance);
            }
        }
        return Flux.just(selectedInstances.isEmpty() ? List.of(instances.get(0)) : selectedInstances);
    }

    private static class CustomServiceInstance implements ServiceInstance {

        private final String id;
        private final String host;
        private final int port;
        private final int weight;

        public CustomServiceInstance(String id, String host, int port, int weight) {
            this.id = id;
            this.host = host;
            this.port = port;
            this.weight = weight;
        }

        @Override
        public String getServiceId() {
            return id;
        }

        @Override
        public String getHost() {
            return host;
        }

        @Override
        public int getPort() {
            return port;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public URI getUri() {
            return URI.create("http://" + host + ":" + port);
        }

        @Override
        public Map<String, String> getMetadata() {
            return Collections.emptyMap();
        }
    }
}
