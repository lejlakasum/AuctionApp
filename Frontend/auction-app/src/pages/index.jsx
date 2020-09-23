export function handleFieldChange(e, setInput) {
    const { name, value } = e.target;
    setInput(prevState => ({
        ...prevState,
        [name]: value
    })
    )
}