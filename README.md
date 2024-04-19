# Policy Verifier

## Description

`PolicyVerifier` is a utility class designed to verify the validity of AWS IAM policy documents in JSON format. It is specifically focused on evaluating policies for access control to AWS resources. 

## Method

### `public static boolean verifyPolicy(String resourcePath);`

Verifies the validity of a JSON policy document located at the given classpath.

#### Parameters

- `resourcePath`: A string representing the classpath to a JSON file.

#### Returns

- `boolean`: Returns `true` if the policy does not contain any resource with a "*", and `false` otherwise.

## Validity Rules

A JSON policy document is considered invalid if:

- Any of the policy statements' `Resource` fields contain a "*".

In all other cases, the policy is considered valid. This includes scenarios where the JSON file is empty or malformed.

There is an exception if file does not exists.

## Scenarios

- Returns `true`:
  - The policy JSON file exists and all policy statements' `Resource` fields contain values other than "*".
  - The policy JSON file is empty or contains malformed JSON (i.e., the JSON is invalid, but does not specifically contain a `Resource` with a "*").

- Returns `false`:
  - The policy JSON file contains at least one policy statement with a `Resource` field that has the value "*".

- Returns `exception`:
  - If file does not exists.

Here is an image showing console:

![image](https://github.com/konris39/RecruExercise/assets/151552959/34218f8b-cf7b-4412-9880-abf9891ff2f3)


## Console Messages

- `ERROR: File does not exist: <resourcePath>`: Means that the file at that path could not be found.
  
- `ERROR: Malformed JSON in <resourcePath>`: Means that the JSON file is malformed. However, this does not affect the validity of the policy, as it is considered valid unless a `Resource` with a "*" is found.

## Usage

To use the `PolicyVerifier`, for new example, you need to:

- Add your .json file to resources, here (There are some example files used in tests and can be used to check the output in console):

![image](https://github.com/konris39/RecruExercise/assets/151552959/3ce83a9a-7005-4371-a05c-16c30c7ab27f)

- In `String[] resourcePaths`, add your .json file, in the same way as in the picture below:

![image](https://github.com/konris39/RecruExercise/assets/151552959/1c23f006-0dea-46d5-911d-0b1555c55d53)

Now run it and everything will work just fine!



