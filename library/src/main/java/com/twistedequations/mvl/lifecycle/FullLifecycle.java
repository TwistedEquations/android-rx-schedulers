package com.twistedequations.mvl.lifecycle;

/**
 * Lifecycle that receives the create, start, restart, resume, pause, stop and destroy events
 */
public interface FullLifecycle extends StartLifecycle, CreateLifecycle, ResumeLifecycle {

}
