1. Purpose of the Prototype

This AdoScript implements a dynamic semantic execution prototype for the TaskWeaver Workflow Modeling Language.

The goal of the script is to:

Interpret a Workflow Model instance

Traverse it structurally and semantically

Simulate workflow execution in a model-driven way

Validate correctness of the workflow structure during execution

The script does not hardcode a specific model instance.
Instead, it dynamically reads any valid Workflow Model created using the TaskWeaver language.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

2. Execution Trigger

Execution is triggered via an external coupling using the ADOxx event:

ON_EVENT "ActivateModelWindow"


When a model window is activated:

The active model ID is stored globally (gTWModelId)

The main execution script (main.asc) is executed

The script automatically checks whether the active model is a valid Workflow Model

This ensures execution is:

Context-aware

Instance-specific

Not triggered on application startup

-------------------------------------------------------------------------------------------------------------------------------------------------------------

3. Model Context Handling

The script supports two execution modes:

Event-based execution

The active model ID is provided by the ActivateModelWindow event

Manual execution fallback

If the script is executed manually, a model selection dialog is shown

This makes the prototype robust and reusable during testing.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

4. Workflow Node Identification

The script dynamically collects all relevant node types from the model:

Event

Task

Gateway

Data Object

These sets are used throughout execution to:

Identify valid workflow nodes

Distinguish SequenceFlow from DataObject associations

Prevent illegal transitions

-------------------------------------------------------------------------------------------------------------------------------------------------------------

5. Start Event Validation

Before traversal begins, the script enforces the rule:

Exactly one Start Event must exist

Execution is aborted if:

No Start Event exists

More than one Start Event exists

This reflects correct BPMN-style workflow semantics.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

6. Traversal Strategy

The workflow is traversed using a model-driven loop:

Starting from the Start Event

Following outgoing connectors

Selecting the first valid workflow node

Explicitly skipping Data Object connections

This is crucial because:

Data Objects are connected via dashed associations

They must not be treated as control-flow elements

Traversal continues until:

An End Event is reached

An Error Event is reached

A structural violation is detected

A step limit is enforced to prevent infinite loops.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

7. Structural Constraints Enforced

The script performs runtime validation and aborts execution when violations occur, including:

- Event → Event transitions

- Start Event not leading to a Task

- Missing outgoing control-flow

- Invalid node types in the control-flow

Multiple or missing Start Events

These checks demonstrate semantic enforcement, not just visualization.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

8. Gateway Semantics
Parallel Gateway

All outgoing Task branches are detected dynamically

The number of branches is counted

Branch execution is narrated (prototype semantics)

Data Object connections are ignored

Exclusive Gateway

One outgoing branch is selected (first valid branch)

Error and End Events are handled explicitly

-------------------------------------------------------------------------------------------------------------------------------------------------------------

9. Semantic Narration

Execution is accompanied by explanatory messages that describe:

Task execution

Data consumption (CSV input)

Parallel branch execution

Exclusive branch selection

JSON output production

Error and end conditions

This narration is intended for demonstration and evaluation, not for real execution.

-------------------------------------------------------------------------------------------------------------------------------------------------------------

10. Cleanup and Isolation

After execution:

Models loaded by the script are unloaded

Global variables are reset

No execution context leaks to other models

This guarantees:

Reproducible behavior

Safe repeated execution

Clean separation between model instances

-------------------------------------------------------------------------------------------------------------------------------------------------------------

11. Summary

This prototype demonstrates:

- Model-driven semantic execution

- Correct handling of workflow vs. data-flow

- Structural validation during traversal

- Dynamic behavior across different model instances

- Proper use of ADOxx events and external coupling

The script fulfills the requirements of a semantic execution prototype for a domain-specific workflow modeling language implemented in ADOxx.