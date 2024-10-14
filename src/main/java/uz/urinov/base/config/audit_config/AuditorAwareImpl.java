package uz.urinov.base.config.audit_config;

import org.springframework.data.domain.AuditorAware;
import uz.urinov.base.profile.entity.ProfileEntity;
import uz.urinov.base.util.SecurityUtil;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<String> getCurrentAuditor() {

        ProfileEntity profile = SecurityUtil.getProfile();
        if (profile == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(profile.getId());

    }

}
