<Container >
    <Typography component="div"
        className="containerQuanliKhachSan" >
        <Grid container direction="row"
            justify="center"
            alignItems="center"
            style={
                { height: '70vh' }
            } >
            < Grid item xs={6} >
                <div className="gridLogin" >
                    <h1> Welcom MinaHotel </h1>
                    <form onSubmit={handleSubmit(onSubmit)} >
                        <div className="form--mod" >
                            <label > Example </label>
                            <input name="example"
                                defaultValue="test"
                                ref={register}
                            />
                        </div >

                        <div className="form--mod" >
                            <label > ExampleRequired </label>
                            <input name="exampleRequired"
                                ref={register({ required: true, maxLength: 10 })}
                            /> {
                                errors.exampleRequired && < p > This field is required </p>}
                        </div>
                        <input type="submit" />
                        <button type="submit"
                            className=".form--mod" > Login </button>
                    </form >
                </div>
            </ Grid >
        </Grid>
    </Typography>
</Container>
