package com.gittigidiyor.quxiotic95.creditapp.validation.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, FirstCheck.class, SecondCheck.class})
public interface OrderedChecks {
}
