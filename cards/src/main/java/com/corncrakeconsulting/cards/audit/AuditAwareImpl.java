package com.corncrakeconsulting.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
@SuppressWarnings({"unused"})
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        return Optional.of("CARDS_MS");
    }
	
}