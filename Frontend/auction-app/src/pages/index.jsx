export function handleFieldChange(e, input, setInput) {
    const { name, value } = e.target;
    setInput(prevState => ({
        ...prevState,
        [name]: value
    })
    )
}