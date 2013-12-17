package com.gandalf.shallnotpass;

import com.gandalf.shallnotpass.health.TemplateHealthCheck;
import com.gandalf.shallnotpass.resources.ShallNotPassResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ShallNotPassService extends Service<ShallNotPassConfiguration> {
    public static void main(String[] args) throws Exception {
        new ShallNotPassService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ShallNotPassConfiguration> bootstrap) {
        bootstrap.setName("shall-not-pass");
    }

    @Override
    public void run(ShallNotPassConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new ShallNotPassResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }
}