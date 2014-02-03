//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu.Finders;

import coypu.AmbiguousException;
import coypu.ElementFound;
import coypu.Match;
import coypu.Options;
import coypu.TextPrecision;

public class FinderOptionsDisambiguationStrategy   implements DisambiguationStrategy
{
    public ElementFound resolveQuery(ElementFinder elementFinder) throws Exception {
        ElementFound[] results;
        if (elementFinder.getOptions().TextPrecision == TextPrecision.PreferExact)
            results = preferExect(elementFinder);
        else
            results = Find(elementFinder); 
        if (elementFinder.getOptions().Match == Match.Single && results.length > 1)
            throw new AmbiguousException(elementFinder.getOptions().BuildAmbiguousMessage(elementFinder.getQueryDescription(), results.length));
         
        if (!results.Any())
            throw elementFinder.getMissingException();
         
        return results.First();
    }

    private static ElementFound[] preferExect(ElementFinder elementFinder) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ results = Find(elementFinder, Options.getExact());
        if (results.Any() || !elementFinder.getSupportsSubstringTextMatching())
            return results;
         
        return Find(elementFinder, Options.getSubstring());
    }

    private static ElementFound[] find(ElementFinder elementFinder, Options preferredOptions) throws Exception {
        return elementFinder.Find((Options.Merge(preferredOptions, elementFinder.getOptions()))).ToArray();
    }

}


