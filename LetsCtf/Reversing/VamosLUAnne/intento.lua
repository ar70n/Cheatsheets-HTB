local main = function(c)
    if #c ~= 8 then return '' end; local g = 0; for f = 1, 8 do g = g +
        (c:sub(f, f) == '1' and 2 ^ (8 - f) or 0) end; print(string.char(g)); return
        string.char(g)
end

print(main())