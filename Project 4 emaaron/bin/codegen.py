import os
import re

root = os.path.dirname(os.path.realpath(__file__))


# def extract_class_name(content):
#     """Extract the class name from the file content."""
#     class_match = re.search(r'public\s+class\s+(\w+)', content)
#     if class_match:
#         return class_match.group(1)
#     return None

def extract_class_name(content):
    """Extract the class name from the file content, including abstract classes."""
    class_match = re.search(r'public\s+(abstract\s+)?class\s+(\w+)', content)
    if class_match:
        return class_match.group(2)  # The second group captures the class name
    return None


def extract_variables(content):
    """Extract instance and static variables from the class."""
    variable_pattern = re.compile(
        r'(private|public|protected)\s+([a-zA-Z<>]+(?:\[\])?)\s+(\w+);')

    variables = []
    for match in variable_pattern.finditer(content):
        visibility, var_type, var_name = match.groups()
        variables.append((visibility, var_type, var_name))

    return variables


# def generate_getter(var_type, var_name):
#     """Generate a getter method for a given variable."""
#     camel_case_name = var_name[0].upper() + var_name[1:]
#     getter = f"""
#   public {var_type} get{camel_case_name}() {{
#     return this.{var_name};
#   }}
#     """
#     return getter

def generate_getter(var_type, var_name):
    """Generate a getter method for a given variable."""
    camel_case_name = var_name[0].upper() + var_name[1:]
    
    if var_type == "boolean":
        getter_name = f"is{camel_case_name}"
    else:
        getter_name = f"get{camel_case_name}"
    
    getter = f"""
  public {var_type} {getter_name}() {{
    return this.{var_name};
  }}
    """
    return getter


def generate_setter(var_type, var_name):
    """Generate a setter method for a given variable."""
    camel_case_name = var_name[0].upper() + var_name[1:]
    setter = f"""
  public void set{camel_case_name}({var_type} {var_name}) {{
    this.{var_name} = {var_name};
  }}
    """
    return setter


def method_exists(content, method_name):
    """Check if a method with the given name already exists in the class."""
    method_pattern = re.compile(rf'public\s+[a-zA-Z<>]+\s+{method_name}\s*\(')
    return method_pattern.search(content) is not None


def append_getters_setters(content, variables):
    """Append getter and setter methods for the given variables to the class."""
    methods = ""
    for visibility, var_type, var_name in variables:
        if visibility in ('private', 'protected'):
            camel_case_name = var_name[0].upper() + var_name[1:]
            # getter_name = f"get{camel_case_name}"
            getter_name = f"is{camel_case_name}" if var_type == "boolean" else f"get{camel_case_name}"
            setter_name = f"set{camel_case_name}"

            # Check if getter already exists
            if not method_exists(content, getter_name):
                methods += generate_getter(var_type, var_name)

            # Check if setter already exists
            if not method_exists(content, setter_name):
                methods += generate_setter(var_type, var_name)

    # Insert the generated methods at the end of the class, before the closing brace
    class_end_index = content.rfind('}')
    new_content = content[:class_end_index] + \
        methods + '\n' + content[class_end_index:]

    return new_content


def process_java_class_file(file_path):
    """Read the Java class file, generate getters and setters, and update the file."""
    with open(file_path, 'r') as file:
        content = file.read()

    class_name = extract_class_name(content)
    if not class_name:
        print(f"Error: No public class found in {file_path}")
        return

    variables = extract_variables(content)
    if not variables:
        print(f"No instance/static variables found in class {class_name}.")
        return

    updated_content = append_getters_setters(content, variables)

    # Write the updated content back to the file
    with open(file_path, 'w') as file:
        file.write(updated_content)

    print(
        f"Getters and setters have been added to the class {class_name} in {os.path.relpath(file_path, root)}")


def find_source_files(root_dir):
    """Search for all .java files, ignoring directories starting with a '.'."""
    java_files = []

    # Traverse the root directory
    for dirpath, dirnames, filenames in os.walk(root_dir):
        # Modify dirnames in-place to skip directories starting with '.'
        dirnames[:] = [d for d in dirnames if not d.startswith('.')]

        # Look for .java files in the remaining directories
        for file in filenames:
            if file.endswith('.java'):
                java_files.append(os.path.join(dirpath, file))

    return java_files


def display_file_options(java_files):
    """Display a list of Java files for the user to select."""
    print("Java files found:")
    for idx, file_path in enumerate(java_files):
        print(f"{idx + 1}. {os.path.relpath(file_path, root)}")

    while True:
        try:
            choice = int(input(
                "\nEnter the number corresponding to the Java file you want to process: ")) - 1
            if 0 <= choice < len(java_files):
                return java_files[choice]
            else:
                print("Invalid choice. Please enter a valid number.")
        except ValueError:
            print("Please enter a number.")


def main():
    java_files = find_source_files(root)

    if not java_files:
        print("No Java files found.")
        return

    # Display the list of files for user to choose
    selected_file = display_file_options(java_files)

    # Process the selected file
    process_java_class_file(selected_file)


if __name__ == "__main__":
    main()
