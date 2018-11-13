/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros;

import java.io.Serializable;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

/**
 *
 * @author desenvolvedor
 */
@Plugin(name = "Log4j2ColetivoJava", category = "com.mycompany.testelog4java.LogPadraoSB", elementType = "appender", printObject = true)
public class Log4j2ColetivoJava extends AbstractAppender {

    private static volatile Log4j2ColetivoJava instance;

    public Log4j2ColetivoJava(final String name, final Filter filter, final Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    @PluginFactory
    public static Log4j2ColetivoJava createAppender(@PluginAttribute("name") String name,
            @PluginAttribute("ignoreExceptions") boolean ignoreExceptions,
            @PluginElement("Layout") Layout layout,
            @PluginElement("Filters") Filter filter) {
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }

        instance = new Log4j2ColetivoJava(name, filter, layout);

        return instance;
    }

    public static Log4j2ColetivoJava getInstance() {
        return instance;
    }

    @Override
    public void append(final LogEvent event) {
        // do something custom
        System.out.println(event.getLevel());
        System.out.println(event.getMessage().getFormattedMessage());
    }
}
