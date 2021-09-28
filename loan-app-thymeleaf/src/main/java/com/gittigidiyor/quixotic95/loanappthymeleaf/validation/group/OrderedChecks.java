package com.gittigidiyor.quixotic95.loanappthymeleaf.validation.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, FirstCheck.class, SecondCheck.class})
public interface OrderedChecks {
}
