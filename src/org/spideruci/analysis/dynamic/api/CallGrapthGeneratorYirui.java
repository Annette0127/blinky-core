package org.spideruci.analysis.dynamic.api;

import org.spideruci.analysis.trace.EventType;
import org.spideruci.analysis.trace.TraceEvent;

import static org.spideruci.analysis.dynamic.Profiler.REAL_OUT;

public class CallGrapthGeneratorYirui extends EmptyProfiler implements IProfiler {


	@Override
	public String description() {
		return "CallGrapthGenerator";
	}

	@Override
	public void startProfiling() {
		REAL_OUT.println("Starting traceing");
	}

	@Override
	public void willInstrumentClass(final String className) {
		REAL_OUT.print("Instrumented Class = ");
		REAL_OUT.println(className);
		REAL_OUT.println();
	}

	@Override
	public void willInstrumentMethod(final TraceEvent e) {
		if (e.getType() != EventType.$$method$$) {
			return;
		}
		String methodName = e.getDeclName();
		String className = e.getDeclOwner();
		long ID = e.getId();
		REAL_OUT.println();
		REAL_OUT.print(e.getType() + "  ");
		REAL_OUT.print(methodName + "  " + className);
		REAL_OUT.println(" ID=" + ID);
	}

	@Override
	public void willInstrumentCode(final TraceEvent e) {
		if (e.getType() == EventType.$enter$ || e.getType() == EventType.$return$) {
			REAL_OUT.println(e.getType() + " " + e.getId() + " " + e.getInsnDeclHostId());
		} else if (e.getType() == EventType.$athrow$) {
			REAL_OUT.println(e.getType() + " " + e.getId() + " " + e.getInsnDeclHostId());
			return;
		}
	}

	@Override
	public void profileMethodEntry(final TraceEvent e) {
		REAL_OUT.print("profileMethodEntry ");
		Thread.dumpStack();
		REAL_OUT.print(" " + e.getInsnFieldName());
		REAL_OUT.print(" " + e.getDeclName());
		REAL_OUT.print(" " + e.getLog());
		REAL_OUT.print(" " + e.getType());
		REAL_OUT.print("  " + e.getExecInsnType());
		REAL_OUT.print("  EventId=" + e.getExecInsnEventId());
		REAL_OUT.print(" Calldepth=" + e.getExecCalldepth());
		REAL_OUT.println(" Timestamp=" + e.getExecTimestamp());
	}

	@Override
	public void profileMethodExit(final TraceEvent e) {
		REAL_OUT.print("profileMethodExit ");
		Thread.dumpStack();
		REAL_OUT.print(" " + e.getInsnFieldName());
		REAL_OUT.print(" " + e.getDeclName());
		REAL_OUT.print(" " + e.getLog());
		REAL_OUT.print(" " + e.getType());
		REAL_OUT.print("  " + e.getExecInsnType());
		REAL_OUT.print("  EventId=" + e.getExecInsnEventId());
		REAL_OUT.print(" Calldepth=" + e.getExecCalldepth());
		REAL_OUT.println(" Timestamp=" + e.getExecTimestamp());
	}

	@Override
	public void endProfiling() {
		REAL_OUT.println("endProfiling");
	}
  
}
